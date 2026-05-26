package com.bajaj.finserv.service;

import com.bajaj.finserv.dto.BfhlResponse;
import java.util.List;

public interface BfhlService {

    BfhlResponse processData(List<String> data);
}
