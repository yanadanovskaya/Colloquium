package ColloqPP.factory;

interface ProductA {
    void operationA();
}
class ConcreteProductA1 implements ProductA {
    @Override
    public void operationA() {
        System.out.println("Operation A from ConcreteProductA1");
    }
}

class ConcreteProductA2 implements ProductA {
    @Override
    public void operationA() {
        System.out.println("Operation A from ConcreteProductA2");
    }
}

interface ProductB {
    void operationB();
}

class ConcreteProductB1 implements ProductB {
    @Override
    public void operationB() {
        System.out.println("Operation B from ConcreteProductB1");
    }
}

class ConcreteProductB2 implements ProductB {
    @Override
    public void operationB() {
        System.out.println("Operation B from ConcreteProductB2");
    }
}

interface AbstractFactory {
    ProductA createProductA();
    ProductB createProductB();
}

class ConcreteFactory1 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB1();
    }
}

class ConcreteFactory2 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB2();
    }
}

class Client {
    private final ProductA productA;
    private final ProductB productB;

    public Client(AbstractFactory factory) {
        productA = factory.createProductA();
        productB = factory.createProductB();
    }

    public void executeOperations() {
        productA.operationA();
        productB.operationB();
    }
}


