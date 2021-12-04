package com.java.ph3.halinamarket.models.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paged<Product> {

    private Page<Product> page;

    private Paging paging;

}
