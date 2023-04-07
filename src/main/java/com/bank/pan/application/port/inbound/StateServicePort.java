package com.bank.pan.application.port.inbound;

import com.bank.pan.application.domain.BrazilStates;
import com.bank.pan.application.domain.ClientDomain;

import java.util.List;

public interface StateServicePort {
   BrazilStates getAll();
}
