import repository.SinglevaluedConfigurationRepositoryMySql;

public class Test {

    public static void main(String[] args) {
        SinglevaluedConfigurationRepositoryMySql repository = new SinglevaluedConfigurationRepositoryMySql();

        repository.selectByAttributeName("Theme");
        System.out.println();
    }
}
