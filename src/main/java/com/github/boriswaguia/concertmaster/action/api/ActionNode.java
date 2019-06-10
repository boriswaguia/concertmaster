package com.github.boriswaguia.concertmaster.action.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ActionNode {
    private String id;
    private String postValidationId;
    private String preValidationId;
    private String onSuccessId;
    private String onPreValidationErrorId;
    private String onPostValidationErrorId;
    private String onExceptionId;
}
