package esprit.tn.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage preConfiguredMessage;
    public void sendMailCommand(String to, String subject)
    {
        MimeMessage msg = mailSender.createMimeMessage();
        String body = "<!doctype html>\r\n" +
                "<html>\r\n" +
                "<head>\r\n" +
                "<meta charset='utf-8'>\r\n" +
                "<title></title>\r\n" +
                "</head>\r\n" +
                "<body style='font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color:#f0f2ea; margin:0; padding:0; color:#333333;'>\r\n" +
                "\r\n" +
                "<table width='100%' bgcolor='#f0f2ea' cellpadding='0' cellspacing='0' border='0'>\r\n" +
                "    <tbody>\r\n" +
                "        <tr>\r\n" +
                "            <td style='padding:40px 0;'>\r\n" +
                "                <!-- begin main block -->\r\n" +
                "                <table cellpadding='0' cellspacing='0' width='608' border='0' align='center'>\r\n" +
                "                    <tbody>\r\n" +
                "                        <tr>\r\n" +
                "                            <td>\r\n" +
                "                                <a href='#' style='display:block; width:407px; height:100px; margin:0 auto 30px;'>\r\n" +
                "                                </a>\r\n" +
                "                                <p style='margin:0 0 36px; text-align:center; font-size:14px; line-height:20px; text-transform:uppercase; color:#626658;'>\r\n" +
                "                                    E-riny\r\n" +
                "                                </p>\r\n" +
                "                                <!-- begin wrapper -->\r\n" +
                "                                <table cellpadding='0' cellspacing='0' border='0' width='100%'>\r\n" +
                "                                    <tbody>\r\n" +
                "                                        \r\n" +
                "                                        \r\n" +
                "                                        <tr>\r\n" +
                "                                            <td colspan='3' rowspan='3' bgcolor='#FFFFFF' style='padding:0 0 30px;'>\r\n" +
                "                                                <!-- begin content -->\r\n" +
                "                                                <center><h1>Bonjour</h1></center>\r\n" +
                "                                                <center><p> Nous avons bien recu votre commande</p></center>\r\n" +
                "                                                <p style='margin:0 30px 33px;; text-align:center; text-transform:uppercase; font-size:24px; line-height:30px; font-weight:bold; color:#484a42;'>\r\n" +
                "                                                    \r\n" +
                "                                                </p>\r\n" +
                "                                                <!-- begin articles -->\r\n" +
                "                                                <table cellpadding='0' cellspacing='0' border='0' width='100%'>\r\n" +
                "                                                    <tbody>\r\n" +
                "                                                        <tr valign='top'>\r\n" +
                "                                                            <td width='30'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                                            <td>\r\n" +
                "                                                                <p style='font-size:14px; line-height:22px; font-weight:bold; color:#333333; margin:0 0 5px;'><a href='http://pixelbuddha.net/' style='color:#6c7e44; text-decoration:none;'></a></p>\r\n" +
                "                                                                <p style='margin:0 0 35px; font-size:12px; line-height:18px; color:#333333;'></p>\r\n" +
                "                                                            </td>\r\n" +
                "                                                            <td width='30'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                                            <td>\r\n" +
                "                                                                </td>\r\n" +
                "                                                            <td width='30'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                                        </tr>\r\n" +
                "                                                        <tr valign='top'>\r\n" +
                "                                                            <td width='30'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                                            <td colspan='3'>\r\n" +
                "                                                                <p style='font-size:14px; line-height:22px; font-weight:bold; color:#333333; margin:0 0 5px;'><a href='http://pixelbuddha.net/' style='color:#6c7e44; text-decoration:none;'>E-RINY 2021</a></p>\r\n" +
                "                                                                <p style='margin:0 0 35px; font-size:12px; line-height:18px; color:#333333;'>Merci d'avoir choisi E-RINY ♥</p>\r\n" +
                "                                                            </td>\r\n" +
                "                                                            <td width='30'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                                        </tr>\r\n" +
                "                                                    </tbody>\r\n" +
                "                                                </table>\r\n" +
                "                                                <!-- /end articles -->\r\n" +
                "                                                <p style='margin:0; border-top:2px solid #e5e5e5; font-size:5px; line-height:5px; margin:0 30px 29px;'>&nbsp;</p>\r\n" +
                "                                                <table cellpadding='0' cellspacing='0' border='0' width='100%'>\r\n" +
                "                                                    <tbody>\r\n" +
                "                                                        <tr valign='top'>\r\n" +
                "                                                            <td width='30'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                                            <td>\r\n" +
                "                                                                <p style='margin:0 0 4px; font-weight:bold; color:#333333; font-size:14px; line-height:22px;'>Consommi Tounsi</p>\r\n" +
                "                                                                <p style='margin:0; color:#333333; font-size:11px; line-height:18px;'>\r\n" +
                "                                                                    La boutique 100% tunisienne<br>\r\n" +
                "                                                                    Help &amp; Support Center: 22 842 875<br>\r\n" +
                "                                                                    Website: <a href='http://pixelbuddha.net/' style='color:#6d7e44; text-decoration:none; font-weight:bold;'>www.yourcompany.com</a>\r\n" +
                "                                                                </p>\r\n" +
                "                                                            </td>\r\n" +
                "                                                            <td width='30'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                                            \r\n" +
                "\r\n" +
                "                                                            <td width='30'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                                        </tr>\r\n" +
                "                                                    </tbody>\r\n" +
                "                                                </table>\r\n" +
                "                                                <!-- end content --> \r\n" +
                "                                            </td>\r\n" +
                "                                            <td width='4' height='4' style='background:url(http://demo.artlance.ru/email/shadow-right-top.png) no-repeat 0 0;'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                        </tr>\r\n" +
                "                                        \r\n" +
                "                                        \r\n" +
                "                                        <tr>\r\n" +
                "                                            <td width='4' style='background:url(http://demo.artlance.ru/email/shadow-left-center.png) repeat-y 100% 0;'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                            <td width='4' style='background:url(http://demo.artlance.ru/email/shadow-right-center.png) repeat-y 0 0;'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                        </tr>\r\n" +
                "                                        \r\n" +
                "                                        <tr> \r\n" +
                "                                            <td width='4' height='4' style='background:url(http://demo.artlance.ru/email/shadow-left-bottom.png) repeat-y 100% 100%;'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                            <td width='4' height='4' style='background:url(http://demo.artlance.ru/email/shadow-right-bottom.png) repeat-y 0 100%;'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                        </tr>\r\n" +
                "                                 \r\n" +
                "                                        <tr>\r\n" +
                "                                            <td width='4' height='4' style='background:url(http://demo.artlance.ru/email/shadow-bottom-corner-left.png) no-repeat 100% 0;'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                            <td width='4' height='4' style='background:url(http://demo.artlance.ru/email/shadow-bottom-left.png) no-repeat 100% 0;'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                            <td height='4' style='background:url(http://demo.artlance.ru/email/shadow-bottom-center.png) repeat-x 0 0;'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                            <td width='4' height='4' style='background:url(http://demo.artlance.ru/email/shadow-bottom-right.png) no-repeat 0 0;'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                            <td width='4' height='4' style='background:url(http://demo.artlance.ru/email/shadow-bottom-corner-right.png) no-repeat 0 0;'><p style='margin:0; font-size:1px; line-height:1px;'>&nbsp;</p></td>\r\n" +
                "                                        </tr>\r\n" +
                "                                    </tbody>\r\n" +
                "                                </table>\r\n" +
                "                                \r\n" +
                "                            </td>\r\n" +
                "                        </tr>\r\n" +
                "                    </tbody>\r\n" +
                "                </table>\r\n" +
                "                <!-- end main block -->\r\n" +
                "            </td>\r\n" +
                "        </tr>\r\n" +
                "    </tbody>\r\n" +
                "</table>\r\n" +
                "</body>\r\n" +
                "</html>\r\n" +
                "" ;
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(msg, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body,true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        mailSender.send(msg);
    }
}
