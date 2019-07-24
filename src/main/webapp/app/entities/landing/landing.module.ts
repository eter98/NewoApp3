import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NewoApp3SharedModule } from 'app/shared';
import {
  LandingComponent,
  LandingDetailComponent,
  LandingUpdateComponent,
  LandingDeletePopupComponent,
  LandingDeleteDialogComponent,
  landingRoute,
  landingPopupRoute
} from './';

const ENTITY_STATES = [...landingRoute, ...landingPopupRoute];

@NgModule({
  imports: [NewoApp3SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    LandingComponent,
    LandingDetailComponent,
    LandingUpdateComponent,
    LandingDeleteDialogComponent,
    LandingDeletePopupComponent
  ],
  entryComponents: [LandingComponent, LandingUpdateComponent, LandingDeleteDialogComponent, LandingDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp3LandingModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
