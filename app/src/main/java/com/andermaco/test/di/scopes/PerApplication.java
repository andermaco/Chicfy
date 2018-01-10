package com.andermaco.test.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Dagger2 scope, so the injection can be reused on the whole application scope (as a singleton).
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApplication {}
