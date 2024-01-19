package com.dmedelacruz.storemodel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestResponse<T> {
    private T content;

    public RestResponse(T content) {
        this.content = content;
    }

    public RestResponse() {

    }

    public static <T> RestResponse of(T content) {
        return new RestResponse(content);
    }

}
