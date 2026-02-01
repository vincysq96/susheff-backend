package com.vincenzo.susheff.common.response;

public record ApiResponse<T>(T data, Metadata metadata) {
}