/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platinesshapi;

import com.jcraft.jsch.Session;

import java.util.List;

import platinesshapi.entity.Alarme;
import platinesshapi.entity.ArboECC;
import platinesshapi.entity.ArboEEC;
import platinesshapi.entity.BilanCollecte;
import platinesshapi.entity.BilanInterface;
import platinesshapi.entity.BilanTraitement;
import platinesshapi.entity.EEC;
import platinesshapi.persistence.AlarmesEECDAO;
import platinesshapi.util.CurrentDate;
import platinesshapi.util.ExportCSV;
import platinesshapi.util.ExportPDF;

/**
 *
 * @author h.el-hayouni
 */
public class PlatineSSHApi {
    
    

    public static void main(String[] args) {
        

        String host = "10.0.0.171";
        String user = "platine";
        String password = "artobj22";
        // String host ="192.168.1.2";
        //String user = "root";
        //String password = "root";
        int port = 22;
//
        Session session = SessionFactory.getSession(host, user, password, port);
        Supervision supervision = null;
        supervision = new Supervision(session);
       // System.out.println(supervision.supp_bill_iecc("25/09/2013", "T1"));
        supervision.sup_platine(CurrentDate.getCurrentDate());
//        //supervision.getAlarmesCollect();
//
//        /**
//         * *****************************************************************
//         */
//        /**
//         * *************************Test ORACLE*****************************
//         */
//        /**
//         * *****************************************************************
//         */
        
////        int alarmesTotal=AlarmesEECDAO.getInstance().getCountAllAlarmes();
    ////        System.out.println(alarmesTotal);
////        List<Alarme> alarmes = AlarmesEECDAO.getInstance().getAllAlarme();
////        System.out.println(alarmes.toString());
////        System.out.println(alarmes.size());
//
//        //AlarmesEECDAO.getInstance().getAll();
//        List<Alarme> alarmes = AlarmesEECDAO.getInstance().getAlarmesByModule("iecc", 20);
//        System.out.println(alarmes.toString());
//        System.out.println(alarmes.size());
//////        System.out.println(alarmes.size());
////        ArboECC arboECC = supervision.getArborescenceECC("Interfaces");
////        System.out.println(arboECC.toString());
////       
////       ArboEEC arboEEC = supervision.getArborescenceEEC("Distribution");
////       System.out.println(arboEEC.toString());
////       ArboEEC arboEEC = supervision.getArborescenceEEC("traitement");
////       System.out.println(arboEEC.toString());
//        //arboEEC.afficherArbo(0);
//        //System.out.println(supervision.sup_alarme());
//        // System.out.println(supervision.list_liais());
//        //    System.out.println(supervision.etatMachine());
////        String alarme=supervision.sup_alarme();
////        System.out.println(alarme);
////        List<EEC> eecs = supervision.list_eec_dist();
////        for (EEC eec : eecs) {
////            if (eec.getCompteursDist()==null)
////                System.out.println("EEC " + eec.getEec() + " compteur null");
////            else
////            System.out.println("EEC "+eec.getEec()+ " compteur not null");
////        }
////          List<EEC> liste = supervision.list_eec_dist();
////          System.out.println(liste);
//////        
////        int countNormale=0, countArret=0;
////        
////        for(EEC sess:liste){
////            if(sess.getEtat().equalsIgnoreCase("arret"))
////                countArret++;
////            else
////                countNormale++;
////        }
////        System.out.println("Arret :"+countArret);
////        System.out.println("Normale: "+countNormale);
////       
//        /**
//         * *********************************Platine*******************************************
//         */
//        //String supplatine=supervision.sup_platine("18/07/2013");
//        //System.out.println(supplatine);
////        List<EEC> list_sess= supervision.list_sess_coll();
////        System.out.println(list_sess);
////        String alarmes = supervision.sup_alarme();
////        System.out.println(alarmes);
////           BilanInterface test=supervision.supp_bill_iecc("13/09/2013","T1");
////            System.out.println("Resultaaaaaaaat"+test);
////        
////        // String test;
////        test = supervision.list_liais();
////         System.out.println(test);
////        EtatMachineColl etat=supervision.etatMachine();
////        System.out.println(etat);
//        //String list_sess_s=supervision.list_sess_s("dc");
//        //System.out.println(list_sess_s);
//        // String testLiaison=supervision.test_liaison("normal");
//        // System.out.println(testLiaison);
//        /**
//         * *********************************Collecte*******************************************
//         */
//        //String suppeec=supervision.supp_eec("18/07/2013");
//        //System.out.println(suppeec);
//        // String supJsNom=supervision.supp_eec_js_eeco("18/07/2013", "IN_CCN");
//        //System.out.println(supJsNom);
//        //String session_etat_listEEC=supervision.list_sess_s_ecol_leeco("dc", "normal","IN_CCN");
//        //System.out.println(session_etat_listEEC);
//        //String visuetat=supervision.visu_etat();
//        //System.out.println(visuetat); 
//        // String testLiaison=supervision.test_liaison("normal");
//        // System.out.println(testLiaison);
//        /**
//         * *********************************Traitement*******************************************
//         */
//        //String supp_trait=supervision.supp_trait("18/07/2013", "T1");
//        //System.out.println(supp_trait);
////         BilanTraitement supp_trait_eeco=supervision.supp_tait_eeco_eec("01/08/2013", "IN_CCN");
////         System.out.println(supp_trait_eeco);
////        
//        /**
//         * *********************************Recyclage*******************************************
//         */
//        // String supp_recy=supervision.supp_recy("18/07/2013", "T1");
//        //System.out.println(supp_recy);
//        //String supp_recy_eeco=supervision.supp_recy_eeco("18/07/2013", "IN_CCN");
//        //System.out.println(supp_recy_eeco);
//        /**
//         * *********************************Correlation*******************************************
//         */
//        //String supp_corr=supervision.supp_corr("18/07/2013", "T1");
//        // System.out.println(supp_corr);
//        /**
//         * *********************************Distribution*******************************************
//         */
//        //BilanDistribution supp_dist=supervision.supp_dist("23/08/2013", "T1");
//        //System.out.println(supp_dist);
////        List<ArticleDistribution> supp_dist_eeco=supervision.supp_dist_eeco_list_cas_article("18/07/2013", "IN_CCN", "T4");
////        System.out.println(supp_dist_eeco);
//        /**
//         * *********************************Interface*******************************************
//         */
//        //String suppecc=supervision.supp_iecc("18/07/2013");
//        //System.out.println(suppecc);
        supervision.disconnect();
        session.disconnect();
    }

}
