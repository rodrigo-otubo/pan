package com.bank.pan.application.port.inbound;

import com.bank.pan.application.domain.State;

import java.util.List;

public interface StateServicePort {
   List<State> getAll();
}
