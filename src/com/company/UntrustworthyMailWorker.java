package com.company;

public class UntrustworthyMailWorker implements MailService {
    MailService[] ms=null;
    RealMailService rms;

    public UntrustworthyMailWorker (MailService[] ms) {
        this.ms=ms;
        this.rms=new RealMailService();

    }

    @Override
    public Sendable processMail(Sendable mail) {

        for (int i=0; i< ms.length;i++) {
            mail=ms[i].processMail(mail);
        }
        return rms.processMail(mail);
    }

    public RealMailService getRealMailService() {
        return this.rms;
    }
    /*
    класс, моделирующий ненадежного работника почты, который вместо того,
    чтобы передать почтовый объект непосредственно в сервис почты,
    последовательно передает этот объект набору третьих лиц, а затем, в конце концов,
     передает получившийся объект непосредственно экземпляру RealMailService.
      У UntrustworthyMailWorker должен быть конструктор от массива MailService
      (результат вызова processMail первого элемента массива
       передается на вход processMail второго элемента, и т. д.) и метод getRealMailService,
        который возвращает ссылку на внутренний экземпляр RealMailService,
         он не приходит масивом в конструкторе и не настраивается извне класса.
     */

    /*
    public class UntrustworthyMailWorker implements MailService {
    private MailService[] mailServise;
    private RealMailService mRealMailService;

    public UntrustworthyMailWorker(MailService[] mail) {
        this.mailServise = new MailService[mail.length];
        for (int i = 0; i < mail.length; i++) {
            this.mailServise[i] = mail[i];
        }
        mRealMailService = new RealMailService();
    }

    public RealMailService getRealMailService() {
        return mRealMailService;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        Sendable buff = null;
        for (int i = 0; i < this.mailServise.length; i++) {

            if (i == 0) {
                buff = this.mailServise[0].processMail(mail);
                continue;
            }

            buff = this.mailServise[i].processMail(buff);
        }
        return getRealMailService().processMail(buff);
    }
}
     */

    //---------------------------------------------------------------
    public static class Spy implements MailService {
        Logger LOGGER;

        public Spy (Logger LOGGER) {
            this.LOGGER = LOGGER;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            MailMessage mMessage = (MailMessage) mail;

            if (mail instanceof MailMessage) {
                if (mMessage.getFrom().contains(AUSTIN_POWERS) || mMessage.getTo().contains(AUSTIN_POWERS)) {
                    LOGGER.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                            new Object[] {mMessage.getFrom(), mMessage.getTo(), mMessage.getMessage()});

                } else {
                    LOGGER.log(Level.INFO, "Usual correspondence: from {0} to {1}", new Object [] {mMessage.getFrom(),
                            mMessage.getTo()});
                }
            }
            return mail;
        }

    }


    public static class Thief implements MailService {
        public int minPrice;
        public int stolenValue;

        public Thief (int minPrice) {
            this.minPrice=minPrice;
        }

        public int getStolenValue () {
            return stolenValue;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            MailPackage mPackage = (MailPackage) mail;
            Package p = mPackage.getContent();

            if (mail instanceof MailPackage) {
                if (p.getPrice() >= minPrice) {
                    MailPackage mp=new MailPackage (mPackage.getFrom(), mPackage.getTo(), new Package ("stones instead of " +
                            p.getContent(), 0));
                    stolenValue+= p.getPrice();
                    return mp;
                }
            }

            return mail;
        }

        public static class Inspector implements MailService {

            @Override
            public Sendable processMail(Sendable mail) {
                MailPackage p = (MailPackage) mail;
                Package pp = p.getContent();

                if (mail instanceof MailPackage) {
                    if (pp.getContent().contains(WEAPONS) || pp.getContent().contains(BANNED_SUBSTANCE)) {
                        throw new IllegalPackageException();
                    }
                    if (pp.getContent().contains("stones")) {
                        throw new StolenPackageException();
                    }
                }

                return mail;
            }
        }

        public static class IllegalPackageException extends RuntimeException {

            public IllegalPackageException () {
                super("Detected weapons or banned substance");
            }
        }

        public static class StolenPackageException extends RuntimeException {

            public StolenPackageException () {
                super ("Detected stones");
            }
        }


        public static class UntrustworthyMailWorker implements MailService {
            MailService [] ms;
            RealMailService rms;

            public UntrustworthyMailWorker (MailService m) {
                ms=m;
                rms = new RealMailService();
            }

            @Override
            public Sendable processMail(Sendable mail) {

                for (int i=0; i.<mail.length; i++) {
                    mail=ms[i].processMail(mail);
                }
                return rms.processMail(mail);
            }

            public RealMailService getRealMailService() {
                return rms;
            }
        }

    }
