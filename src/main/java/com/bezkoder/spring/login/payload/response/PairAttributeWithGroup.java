package com.bezkoder.spring.login.payload.response;


public class PairAttributeWithGroup {
    private AttributeGroupResponse attributeGroupResponse;
    private AttributeResponse attributeResponse;

    public PairAttributeWithGroup(AttributeGroupResponse attributeGroupResponse, AttributeResponse attributeResponse) {
        this.attributeGroupResponse = attributeGroupResponse;
        this.attributeResponse = attributeResponse;
    }

    public AttributeGroupResponse getAttributeGroupResponse() {
        return attributeGroupResponse;
    }

    public AttributeResponse getAttributeResponse() {
        return attributeResponse;
    }
}
