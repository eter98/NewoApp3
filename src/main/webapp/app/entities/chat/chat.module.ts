import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NewoApp3SharedModule } from 'app/shared';
import {
  ChatComponent,
  ChatDetailComponent,
  ChatUpdateComponent,
  ChatDeletePopupComponent,
  ChatDeleteDialogComponent,
  chatRoute,
  chatPopupRoute
} from './';

const ENTITY_STATES = [...chatRoute, ...chatPopupRoute];

@NgModule({
  imports: [NewoApp3SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [ChatComponent, ChatDetailComponent, ChatUpdateComponent, ChatDeleteDialogComponent, ChatDeletePopupComponent],
  entryComponents: [ChatComponent, ChatUpdateComponent, ChatDeleteDialogComponent, ChatDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp3ChatModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
