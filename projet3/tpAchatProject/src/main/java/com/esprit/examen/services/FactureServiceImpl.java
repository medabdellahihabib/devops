package com.esprit.examen.services;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.DetailFactureRepository;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.repositories.ProduitRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class FactureServiceImpl implements IFactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private OperateurRepository operateurRepository;

    @Autowired
    private DetailFactureRepository detailFactureRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ReglementServiceImpl reglementService;

    // Constructor for dependency initialization (if needed)
    public FactureServiceImpl(FactureRepository factureRepository,
                              OperateurRepository operateurRepository,
                              DetailFactureRepository detailFactureRepository,
                              FournisseurRepository fournisseurRepository,
                              ProduitRepository produitRepository,
                              ReglementServiceImpl reglementService) {
        this.factureRepository = factureRepository;
        this.operateurRepository = operateurRepository;
        this.detailFactureRepository = detailFactureRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.produitRepository = produitRepository;
        this.reglementService = reglementService;
    }

    @Override
    public List<Facture> retrieveAllFactures() {
        List<Facture> factures = (List<Facture>) factureRepository.findAll();
        for (Facture facture : factures) {
            log.info("Facture : " + facture);
        }
        return factures;
    }

    @Override
    public Facture addFacture(Facture f) {
        return factureRepository.save(f);
    }

    // Add details to the invoice
    public Facture addDetailsFacture(Facture f, Set<DetailFacture> detailsFacture) {
        float montantFacture = 0;
        float montantRemise = 0;
        for (DetailFacture detail : detailsFacture) {
            // Retrieve the product
            Produit produit = produitRepository.findById(detail.getProduit().getIdProduit()).orElse(null);
            if (produit != null) {
                // Calculate total amount for each detail invoice
                float prixTotalDetail = detail.getQteCommandee() * produit.getPrix();
                // Calculate the discount amount for each detail invoice
                float montantRemiseDetail = (prixTotalDetail * detail.getPourcentageRemise()) / 100;
                float prixTotalDetailRemise = prixTotalDetail - montantRemiseDetail;
                detail.setMontantRemise(montantRemiseDetail);
                detail.setPrixTotalDetail(prixTotalDetailRemise);
                // Calculate the total amount for the invoice
                montantFacture += prixTotalDetailRemise;
                // Calculate the discount amount for the invoice
                montantRemise += montantRemiseDetail;
                detailFactureRepository.save(detail);
            }
        }
        f.setMontantFacture(montantFacture);
        f.setMontantRemise(montantRemise);
        return f;
    }

    @Override
    public void cancelFacture(Long factureId) {
        Facture facture = factureRepository.findById(factureId).orElse(new Facture());
        facture.setArchivee(true);
        factureRepository.save(facture);
        // Method 02 (With JPQL)
        factureRepository.updateFacture(factureId);
    }

    @Override
    public Facture retrieveFacture(Long factureId) {
        Facture facture = factureRepository.findById(factureId).orElse(null);
        log.info("Facture :" + facture);
        return facture;
    }

    @Override
    public List<Facture> getFacturesByFournisseur(Long idFournisseur) {
        Fournisseur fournisseur = fournisseurRepository.findById(idFournisseur).orElse(null);
        return fournisseur != null ? (List<Facture>) fournisseur.getFactures() : null;
    }

    @Override
    public void assignOperateurToFacture(Long idOperateur, Long idFacture) {
        Facture facture = factureRepository.findById(idFacture).orElse(null);
        Operateur operateur = operateurRepository.findById(idOperateur).orElse(null);
        if (operateur != null && facture != null) {
            operateur.getFactures().add(facture);
            operateurRepository.save(operateur);
        }
    }

    @Override
    public float pourcentageRecouvrement(Date startDate, Date endDate) {
        float totalFacturesEntreDeuxDates = factureRepository.getTotalFacturesEntreDeuxDates(startDate, endDate);
        float totalRecouvrementEntreDeuxDates = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
        return totalFacturesEntreDeuxDates > 0 ? (totalRecouvrementEntreDeuxDates / totalFacturesEntreDeuxDates) * 100 : 0;
    }
}
