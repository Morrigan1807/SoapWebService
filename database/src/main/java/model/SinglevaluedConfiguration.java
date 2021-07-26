package model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Builder
@Table(name = "general_singlevalued_configuration")
public class SinglevaluedConfiguration {
    @Id
    @Column(name = "attribute_name")
    private String attributeName;
    @Column(name = "attribute_value")
    private String attributeValue;
    @Column(name = "attribute_desc")
    private String attributeDesc;

    public SinglevaluedConfiguration(String attributeName, String attributeValue, String attributeDesc) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.attributeDesc = attributeDesc;
    }
}
