/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import javafx.scene.control.Alert;

/**
 *
 * @author Administrator
 */
public class alerts {

    public static Alert alertUpdate = info(Alert.AlertType.CONFIRMATION,
            "التعديل", "تأكيد التعديل", "تأكد من المعلومات قبل التعديل");
    public static final Alert alertUpdate_ar = info(Alert.AlertType.CONFIRMATION,
            "التعديل", "تأكيد التعديل", "تأكد من المعلومات قبل التعديل");
    
    public static Alert alertDelete_ar = info(Alert.AlertType.CONFIRMATION,
            "الحذف", "تأكيد الحذف", "تأكد قبل الحذف");
    public static Alert alertDelete = info(Alert.AlertType.CONFIRMATION,
            "suprimer", "suprimer confirmation", "Vérifier avant de supprimer");

    public static Alert confirmFactureAlert_ar = info(Alert.AlertType.INFORMATION,
            "تأكيد", "تأكيد الفاتورة", "يجب تأكيد فواتير البيع");
    public static Alert confirmFactureAlert = info(Alert.AlertType.INFORMATION,
            "confirmation", "Veuillez confirmer la facture", "");
    
    public static Alert aboutAlert = info(Alert.AlertType.INFORMATION,
            "About", "Le programme est fait par", "Zebda Ahmed Yacine et Kadri Harran");
    public static Alert aboutAlert_ar = info(Alert.AlertType.INFORMATION,
            "حول البرنامج", "البرنامج من تطوير", "قادري حران و زبدة أحمد ياسين");
    
    

    public static Alert info(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

}
