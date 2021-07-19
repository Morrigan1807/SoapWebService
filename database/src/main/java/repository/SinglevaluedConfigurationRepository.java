package repository;

import model.SinglevaluedConfiguration;

import java.util.List;

public interface SinglevaluedConfigurationRepository {

    void delete(SinglevaluedConfiguration singlevaluedConfiguration);

    List<SinglevaluedConfiguration> selectAll();

    SinglevaluedConfiguration selectByAttributeName(String attributeName);

    void insert(SinglevaluedConfiguration generalSinglevaluedConfiguration);

    void update(SinglevaluedConfiguration generalSinglevaluedConfiguration);
}
