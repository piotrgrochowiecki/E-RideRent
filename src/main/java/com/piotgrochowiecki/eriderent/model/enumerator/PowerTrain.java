package com.piotgrochowiecki.eriderent.model.enumerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum PowerTrain {

    AWD("All wheels drive", "Napęd na wszystkie koła"),
    FWD("Front wheels drive", "Napęd na przednie koła"),
    RWD("Rear wheels drive", "Napęd na tylne koła");

    final String powerTrainTypeEn;
    final String powerTrainTypePl;

}
