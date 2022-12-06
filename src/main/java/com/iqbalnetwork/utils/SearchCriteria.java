package com.iqbalnetwork.utils;

import com.iqbalnetwork.utils.constant.QueryOperator;
import com.iqbalnetwork.utils.constant.SearchOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchCriteria {
    private String field;
    private String value;
    private QueryOperator queryOperator;
    private SearchOperation searchOperation;
}
