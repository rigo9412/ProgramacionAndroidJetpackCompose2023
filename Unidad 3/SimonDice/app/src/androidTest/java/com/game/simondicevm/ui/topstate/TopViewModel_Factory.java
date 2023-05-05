package com.game.simondicevm.ui.topstate;

import com.rigo.simondice.domain.repository.SimonGameRepository;

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
public final class TopViewModel_Factory implements Factory<TopViewModel> {
  private final Provider<SimonGameRepository> simonGameRepositoryProvider;

  public TopViewModel_Factory(Provider<SimonGameRepository> simonGameRepositoryProvider) {
    this.simonGameRepositoryProvider = simonGameRepositoryProvider;
  }

  @Override
  public TopViewModel get() {
    return newInstance(simonGameRepositoryProvider.get());
  }

  public static TopViewModel_Factory create(
      Provider<SimonGameRepository> simonGameRepositoryProvider) {
    return new TopViewModel_Factory(simonGameRepositoryProvider);
  }

  public static TopViewModel newInstance(SimonGameRepository simonGameRepository) {
    return new TopViewModel(simonGameRepository);
  }
}
