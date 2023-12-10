package ColloqPP.factory;

import org.junit.Test;

public class ClientTest {
    @Test
    public void testExecuteOperations() {
        AbstractFactory factoryMock = new AbstractFactory() {
            @Override
            public ProductA createProductA() {
                return new ProductAMock();
            }

            @Override
            public ProductB createProductB() {
                return new ProductBMock();
            }
        };
        Client client = new Client(factoryMock);

        client.executeOperations();

    }

    private class ProductAMock implements ProductA {
        @Override
        public void operationA() {
        }
    }

    private class ProductBMock implements ProductB {
        @Override
        public void operationB() {
        }
    }
}
