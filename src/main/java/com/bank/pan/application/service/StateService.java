package com.bank.pan.application.service;

import com.bank.pan.application.domain.BrazilStates;
import com.bank.pan.application.domain.State;
import com.bank.pan.application.port.inbound.StateServicePort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StateService implements StateServicePort {
    @Override
    public List<State> getAll() {
        return Arrays.stream(BrazilStates.values()).toList().stream().map( state -> new State(state.name())).collect(Collectors.toList());
    }
}
