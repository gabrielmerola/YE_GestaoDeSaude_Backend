package com.maua.yegestaodesaude.shared.services;

public class EmailTemplates {
    public static String forgotPasswordMailHtml(String code) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<style>\n" +
                "    body {\n" +
                "        font-family: 'Roboto', sans-serif;\n" +
                "    }\n" +
                "</style>\n" +
                "<body>\n" +
                "    <div style=\"background-color: #e9ecef; width: 100%; height: 720px; display: flex; flex-direction: column; align-items: center;\">\n"
                +
                "        <img src=\"https://i.imgur.com/QiSEbPT.png\" alt=\"NotemauaLogo\" style=\"width: 200px; height: 64px; object-fit: contain; margin-top: 40px;\"/>\n"
                +
                "        <div style=\"background-color: #fff; width: 80%; height: 60%; margin-top: 40px; box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.2); padding-left: 20px; padding-right: 20px;\">\n"
                +
                "            <h1 style=\"font-size:18px; margin-top: 40px; margin-bottom: 28px\">Recupere sua sua senha ⚠️</h1>\n"
                +
                "            <p style=\"font-size: 14px; font-weight: 300; margin-right: 20px; margin-bottom: 80px;\">Geramos um código para você!</p>\n"
                +
                "            <p style=\"font-size:20px; font-weight: bold; text-align: center;\">" + code + "</p>\n" +
                "            <p style=\"font-size: 14px; font-weight: 300; margin-right: 20px; margin-top: 80px\">É <strong>importante</strong> que você saiba que este código é único para esse pedido de troca de senha! Então caso precise reenviar o pedido de troca de senha, será gerado um novo código e este desse email não será mais válido.</p>\n"
                +
                "        </div>\n" +
                "        <p style=\"font-size: 12px; font-weight: 300; margin-right: 20px; text-align: center; padding-left: 28px; padding-right: 28px; margin-top: 32px\">Você recebeu este email porquê recebemos um pedido para que seja realizado o seu primeiro acesso. Se você nõo requisitou o seu primeiro acesso você pode ignorar este email.</p>\n"
                +
                "        <p style=\"font-size: 12px; font-weight: 300; margin-right: 20px; text-align: center; padding-left: 28px; padding-right: 28px;\">&#9400; Todos os direitos são reservados.</p>\n"
                +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}