package com.senchuk.project.model.dto;

import com.senchuk.project.model.ReferenceData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;
import java.util.List;

@Table(name = "reference_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreditOptionDto {

    private List<ReferenceData> creditType;
    private List<ReferenceData> currencyType;
    private List<ReferenceData> creditTerm;

}
