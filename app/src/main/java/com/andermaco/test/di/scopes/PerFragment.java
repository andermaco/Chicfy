package com.andermaco.test.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Dagger2 scope, so the injection can be reused on a fragment's scope.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {}
