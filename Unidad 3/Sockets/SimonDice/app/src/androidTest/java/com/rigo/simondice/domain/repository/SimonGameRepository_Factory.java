package com.rigo.simondice.domain.repository;

import com.rigo.simondice.domain.service.network.IApiService;
import com.squareup.moshi.Moshi;

import javax.annotation.processing.Generated;
import javax.inject.Provider;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class SimonGameRepository_Factory implements Factory<SimonGameRepository> {
  private final Provider<IApiService> apiServiceProvider;

  private final Provider<Moshi> moshiProvider;

  public SimonGameRepository_Factory(Provider<IApiService> apiServiceProvider,
      Provider<Moshi> moshiProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public SimonGameRepository get() {
    return newInstance(apiServiceProvider.get(), moshiProvider.get());
  }

  public static SimonGameRepository_Factory create(Provider<IApiService> apiServiceProvider,
      Provider<Moshi> moshiProvider) {
    return new SimonGameRepository_Factory(apiServiceProvider, moshiProvider);
  }

  public static SimonGameRepository newInstance(IApiService apiService, Moshi moshi) {
    return new SimonGameRepository(apiService, moshi);
  }
}
