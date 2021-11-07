package com.company;

public class Thief implements MailService {
    /*
    вор, который ворует самые ценные посылки и игнорирует все остальное.
    Вор принимает в конструкторе переменную int – минимальную стоимость посылки, которую он будет воровать.
    Также, в данном классе должен присутствовать метод getStolenValue, который возвращает суммарную стоимость
    всех посылок, которые он своровал.
    Воровство происходит следующим образом: вместо посылки, которая пришла вору, он отдает новую, такую же,
    только с нулевой ценностью и содержимым посылки "stones instead of {content}".
     */
    public static int minPrice=0;
    public int summThief=0;

    public Thief (int minPrice) {

        this.minPrice=minPrice;
    }

    public int getStolenValue () {
        return summThief;
    }



    @Override
    public Sendable processMail(Sendable mailThief) {
            if (mailThief instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mailThief;
                Package pack = mailPackage.getContent();
                if (pack.getPrice()>=minPrice) {
                    summThief+=pack.getPrice();
                    return new MailPackage(mailThief.getFrom(), mailThief.getTo(),
                            new Package("stones instead of " + pack.getContent(),0));
                }
            }

        return mailThief;
    }
}
