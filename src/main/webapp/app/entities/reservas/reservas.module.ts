import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NewoApp3SharedModule } from 'app/shared';
import {
  ReservasComponent,
  ReservasDetailComponent,
  ReservasUpdateComponent,
  ReservasDeletePopupComponent,
  ReservasDeleteDialogComponent,
  reservasRoute,
  reservasPopupRoute
} from './';

const ENTITY_STATES = [...reservasRoute, ...reservasPopupRoute];

@NgModule({
  imports: [NewoApp3SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ReservasComponent,
    ReservasDetailComponent,
    ReservasUpdateComponent,
    ReservasDeleteDialogComponent,
    ReservasDeletePopupComponent
  ],
  entryComponents: [ReservasComponent, ReservasUpdateComponent, ReservasDeleteDialogComponent, ReservasDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp3ReservasModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
