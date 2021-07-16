package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SinglevaluedConfiguration {

    private String attributeName;
    private String attributeValue;
    private String attributeDesc;
}
