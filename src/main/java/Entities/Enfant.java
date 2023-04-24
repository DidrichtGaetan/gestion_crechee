package Entities;

import java.io.Serializable;
import java.sql.Date;

public class Enfant implements Serializable {

    private int num;
    private String num_contrat;
    private String nom;
    private String prenom;
    private Date date_naissance;
    private Date date_entree;
    private Date date_sortie;
    private int lundi_am;
    private int lundi_pm;
    private int mardi_am;
    private int mardi_pm;
    private int mercredi_am;
    private int mercredi_pm;
    private int jeudi_am;
    private int jeudi_pm;
    private int vendredi_am;
    private int vendredi_pm;
    private int montant_jour;
    private int montant_forfait;
    private String commentaire;
    private String mere;
    private String num_mere;
    private String email_mere;
    private String pere;
    private String num_pere;
    private String email_pere;

    public Enfant() {

    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNum_contrat() {
        return num_contrat;
    }

    public void setNum_contrat(String num_contrat) {
        this.num_contrat = num_contrat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Date getDate_entree() {
        return date_entree;
    }

    public void setDate_entree(Date date_entree) {
        this.date_entree = date_entree;
    }

    public Date getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(Date date_sortie) {
        this.date_sortie = date_sortie;
    }

    public int getLundi_am() {
        return lundi_am;
    }

    public void setLundi_am(int lundi_am) {
        this.lundi_am = lundi_am;
    }

    public int getLundi_pm() {
        return lundi_pm;
    }

    public void setLundi_pm(int lundi_pm) {
        this.lundi_pm = lundi_pm;
    }

    public int getMardi_am() {
        return mardi_am;
    }

    public void setMardi_am(int mardi_am) {
        this.mardi_am = mardi_am;
    }

    public int getMardi_pm() {
        return mardi_pm;
    }

    public void setMardi_pm(int mardi_pm) {
        this.mardi_pm = mardi_pm;
    }

    public int getMercredi_am() {
        return mercredi_am;
    }

    public void setMercredi_am(int mercredi_am) {
        this.mercredi_am = mercredi_am;
    }

    public int getMercredi_pm() {
        return mercredi_pm;
    }

    public void setMercredi_pm(int mercredi_pm) {
        this.mercredi_pm = mercredi_pm;
    }

    public int getJeudi_am() {
        return jeudi_am;
    }

    public void setJeudi_am(int jeudi_am) {
        this.jeudi_am = jeudi_am;
    }

    public int getJeudi_pm() {
        return jeudi_pm;
    }

    public void setJeudi_pm(int jeudi_pm) {
        this.jeudi_pm = jeudi_pm;
    }

    public int getVendredi_am() {
        return vendredi_am;
    }

    public void setVendredi_am(int vendredi_am) {
        this.vendredi_am = vendredi_am;
    }

    public int getVendredi_pm() {
        return vendredi_pm;
    }

    public void setVendredi_pm(int vendredi_pm) {
        this.vendredi_pm = vendredi_pm;
    }

    public int getMontant_jour() {
        return montant_jour;
    }

    public void setMontant_jour(int montant_jour) {
        this.montant_jour = montant_jour;
    }

    public int getMontant_forfait() {
        return montant_forfait;
    }

    public void setMontant_forfait(int montant_forfait) {
        this.montant_forfait = montant_forfait;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getMere() {
        return mere;
    }

    public void setMere(String mere) {
        this.mere = mere;
    }

    public String getNum_mere() {
        return num_mere;
    }

    public void setNum_mere(String num_mere) {
        this.num_mere = num_mere;
    }

    public String getEmail_mere() {
        return email_mere;
    }

    public void setEmail_mere(String email_mere) {
        this.email_mere = email_mere;
    }

    public String getPere() {
        return pere;
    }

    public void setPere(String pere) {
        this.pere = pere;
    }

    public String getNum_pere() {
        return num_pere;
    }

    public void setNum_pere(String num_pere) {
        this.num_pere = num_pere;
    }

    public String getEmail_pere() {
        return email_pere;
    }

    public void setEmail_pere(String email_pere) {
        this.email_pere = email_pere;
    }

    @Override
    public String toString() {
        return
                "num=" + num +
                ", num_contrat=" + num_contrat +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_naissance=" + date_naissance +
                ", date_entree=" + date_entree +
                ", date_sortie=" + date_sortie +
                ", lundi_am=" + lundi_am +
                ", lundi_pm=" + lundi_pm +
                ", mardi_am=" + mardi_am +
                ", mardi_pm=" + mardi_pm +
                ", mercredi_am=" + mercredi_am +
                ", mercredi_pm=" + mercredi_pm +
                ", jeudi_am=" + jeudi_am +
                ", jeudi_pm=" + jeudi_pm +
                ", vendredi_am=" + vendredi_am +
                ", vendredi_pm=" + vendredi_pm +
                ", montant_jour=" + montant_jour +
                ", montant_forfait=" + montant_forfait +
                ", commentaire='" + commentaire + '\'' +
                ", mere='" + mere + '\'' +
                ", num_mere=" + num_mere +
                ", email_mere='" + email_mere + '\'' +
                ", pere='" + pere + '\'' +
                ", num_pere=" + num_pere +
                ", email_pere='" + email_pere + '\'' +
                '}';
    }
}
