package com.rigo.simondice.domain.di;

import com.rigo.simondice.domain.service.network.IApiService;
import com.squareup.moshi.Moshi;

import javax.annotation.processing.Generated;
import javax.inject.Provider;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvidesApiServiceFactory implements Factory<IApiService> {
  private final Provider<Moshi> moshiProvider;

  public AppModule_ProvidesApiServiceFactory(Provider<Moshi> moshiProvider) {
    this.moshiProvider = moshiProvider;
  }

  @Override
  public IApiService get() {
    return providesApiService(moshiProvider.get());
  }

  public static AppModule_ProvidesApiServiceFactory create(Provider<Moshi> moshiProvider) {
    return new AppModule_ProvidesApiServiceFactory(moshiProvider);
  }

  public static IApiService providesApiService(Moshi moshi) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providesApiService(moshi));
  }
}
