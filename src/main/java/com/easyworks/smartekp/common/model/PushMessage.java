package com.easyworks.smartekp.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PushMessage {
    private int regId;
    private String pushMessage;
    private boolean isUpdated;
    private String updateDate;
    private boolean isChecked;
    private String checkDate;
}