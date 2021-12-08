package app.factory;

public  class AbstractFactory {


    private static AbstractFactory instance;

    private AbstractFactory(){

    }

    public static AbstractFactory getInstance(){
        if(instance == null)
            instance = new AbstractFactory();
        return instance;
    }

    public NameAlrExists createPopup(ErrorType type) {
        switch (type) {
            case EMPTY_NAME -> {
                return new NameAlrExists();
            }
        }
        return null;
    }

}
