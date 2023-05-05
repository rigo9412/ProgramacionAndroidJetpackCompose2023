package com.rigo.simondice.domain.di;

import com.rigo.simondice.domain.repository.SimonGameRepository;
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
public final class AppModule_ProvideSimonGameRepositoryFactory implements Factory<SimonGameRepository> {
  private final Provider<IApiService> apiServiceProvider;

  private final Provider<Moshi> moshiProvider;

  public AppModule_ProvideSimonGameRepositoryFactory(Provider<IApiService> apiServiceProvider,
      Provider<Moshi> moshiProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public SimonGameRepository get() {
    return provideSimonGameRepository(apiServiceProvider.get(), moshiProvider.get());
  }

  public static AppModule_ProvideSimonGameRepositoryFactory create(
      Provider<IApiService> apiServiceProvider, Provider<Moshi> moshiProvider) {
    return new AppModule_ProvideSimonGameRepositoryFactory(apiServiceProvider, moshiProvider);
  }

  public static SimonGameRepository provideSimonGameRepository(IApiService apiService,
      Moshi moshi) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSimonGameRepository(apiService, moshi));
  }
}
