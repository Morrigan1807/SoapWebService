package com.niagara.provisioning.ws.jaxws;

import model.SinglevaluedConfiguration;
import org.apache.http.HttpStatus;
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

        if (configurationDB == null) {
            response.setResultCode(HttpStatus.SC_NOT_FOUND);
            ErrorType error = new ErrorType();
            error.setErrorDescription("Configuration not found");
            response.setError(error);

            return response;
        }
        response.setConfiguration(Util.convertConfigurationFromDbToService(configurationDB));
        response.setResultCode(HttpStatus.SC_OK);

        return response;
    }

    @Override
    public ResponseMessageType createConfiguration(Configuration configuration) {
        repository.insert(Util.convertConfigurationFromServiceToDb(configuration));

        ResponseMessageType response = new ResponseMessageType();
        response.setResultCode(HttpStatus.SC_CREATED);

        return response;
    }

    @Override
    public ResponseMessageType deleteConfiguration(Configuration configuration) {
        repository.delete(Util.convertConfigurationFromServiceToDb(configuration));

        ResponseMessageType response = new ResponseMessageType();
        response.setResultCode(HttpStatus.SC_NO_CONTENT);

        return response;
    }

    @Override
    public ResponseMessageType updateConfiguration(Configuration configuration) {
        repository.update(Util.convertConfigurationFromServiceToDb(configuration));

        ResponseMessageType response = new ResponseMessageType();
        response.setResultCode(HttpStatus.SC_OK);

        return response;
    }

    @Override
    public ResponseMessageWithAllConfigurations getAllConfigurations() {
        List<SinglevaluedConfiguration> configurationsDB = repository.selectAll();

        ResponseMessageWithAllConfigurations response = new ResponseMessageWithAllConfigurations();

        if (configurationsDB.size() == 0) {
            response.setResultCode(HttpStatus.SC_NOT_FOUND);
            ErrorType error = new ErrorType();
            error.setErrorDescription("Configurations not found");
            response.setError(error);

            return response;
        }

        response.setConfigurations(Util.convertListOfConfigurationsFromDbToService(configurationsDB));
        response.setResultCode(HttpStatus.SC_OK);

        return response;
    }
}
