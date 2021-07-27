package com.niagara.provisioning.ws.jaxws;

import model.SinglevaluedConfiguration;
import repository.SinglevaluedConfigurationRepository;
import repository.SinglevaluedConfigurationRepositoryMySql;

import javax.jws.WebService;
import java.util.List;

@WebService(portName = "Provisioning_WSSOAP", targetNamespace = "http://com.niagara.provisioning.ws", endpointInterface = "com.niagara.provisioning.ws.jaxws.ProvisioningWS")
public class ProvisioningWSImpl implements ProvisioningWS {

    private final SinglevaluedConfigurationRepository repository = new SinglevaluedConfigurationRepositoryMySql();

    @Override
    public ResponseMessageWithConfiguration getConfiguration(ConfigurationName configurationName) {
        SinglevaluedConfiguration configurationDB = repository.selectByAttributeName(configurationName.getName());

        ResponseMessageWithConfiguration response = new ResponseMessageWithConfiguration();
        response.setConfiguration(Util.convertConfigurationFromDbToService(configurationDB));
        response.setResultCode(200);

        return response;
    }

    @Override
    public ResponseMessageType createConfiguration(Configuration configuration) {
        repository.insert(Util.convertConfigurationFromServiceToDb(configuration));

        ResponseMessageType response = new ResponseMessageType();
        response.setResultCode(200);

        return response;
    }

    @Override
    public ResponseMessageType deleteConfiguration(Configuration configuration) {
        repository.delete(Util.convertConfigurationFromServiceToDb(configuration));

        ResponseMessageType response = new ResponseMessageType();
        response.setResultCode(204);

        return response;
    }

    @Override
    public ResponseMessageType updateConfiguration(Configuration configuration) {
        repository.update(Util.convertConfigurationFromServiceToDb(configuration));

        ResponseMessageType response = new ResponseMessageType();
        response.setResultCode(200);

        return response;
    }

    @Override
    public ResponseMessageWithAllConfigurations getAllConfigurations(ListOfConfigurations listOfConfigurations) {
        List<SinglevaluedConfiguration> configurationsDB = repository.selectAll();

        ResponseMessageWithAllConfigurations response = new ResponseMessageWithAllConfigurations();
        response.setConfigurations(Util.convertListOfConfigurationsFromDbToService(configurationsDB));
        response.setResultCode(200);

        return response;
    }
}
