package com.company;

public class Inspector implements MailService{
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    @Override
    public Sendable processMail(Sendable mail) {

        if (mail instanceof MailPackage) {
            MailPackage mailInspector=(MailPackage) mail;
            Package packageInspector=mailInspector.getContent();

            if (packageInspector.getContent().contains(WEAPONS) || packageInspector.getContent().contains(BANNED_SUBSTANCE)){
                throw new IllegalPackageException();
            }
            if (packageInspector.getContent().equals("stones")) {
            throw new StolenPackageException();
            }
        }
        return mail;
    }
    /*
    Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде исключения,
    если была обнаружена подобная посылка. Если он заметил запрещенную посылку
    с одним из запрещенных содержимым ("weapons" и "banned substance"),
    то он бросает IllegalPackageException.
    Если он находит посылку, состоящую из камней (содержит слово "stones"),
    то тревога прозвучит в виде StolenPackageException.
    Оба исключения вы должны объявить самостоятельно в виде непроверяемых исключений.
     */
}
