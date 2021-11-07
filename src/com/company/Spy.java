package com.company;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Spy implements MailService {
    /*
    шпион, который логгирует о всей почтовой переписке, которая проходит через его руки.
    Объект конструируется от экземпляра Logger, с помощью которого шпион будет сообщать о всех действиях.
    Он следит только за объектами класса MailMessage и пишет в логгер следующие сообщения
    (в выражениях нужно заменить части в фигурных скобках на значения полей почты):
2.1) Если в качестве отправителя или получателя указан "Austin Powers", то нужно написать в лог
сообщение с уровнем WARN: Detected target mail correspondence: from {from} to {to} "{message}"
2.2) Иначе, необходимо написать в лог сообщение с уровнем INFO: Usual correspondence: from {from} to {to}
     */
    public static final String AUSTIN_POWERS = "Austin Powers";
    public Logger LOGGER;

    public Spy (Logger LOGGER) {
        this.LOGGER=LOGGER;
    }

    @Override
    public Sendable processMail(Sendable mail) {

        if (mail instanceof MailMessage) {
            MailMessage mailMessage = (MailMessage) mail;

            if (mailMessage.getFrom().contains(AUSTIN_POWERS) || mailMessage.getTo().contains(AUSTIN_POWERS)) {
                LOGGER.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                        new Object[]{mailMessage.getFrom(), mailMessage.getTo(), mailMessage.getMessage()});
            } else {
                LOGGER.log(Level.INFO, "Usual correspondence: from {0} to {1}",
                        new Object[] {mailMessage.getFrom(), mailMessage.getTo()});
            }
        }
        return mail;
    }
}
