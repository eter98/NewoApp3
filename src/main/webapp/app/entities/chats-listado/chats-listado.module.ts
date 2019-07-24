import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NewoApp3SharedModule } from 'app/shared';
import {
  ChatsListadoComponent,
  ChatsListadoDetailComponent,
  ChatsListadoUpdateComponent,
  ChatsListadoDeletePopupComponent,
  ChatsListadoDeleteDialogComponent,
  chatsListadoRoute,
  chatsListadoPopupRoute
} from './';

const ENTITY_STATES = [...chatsListadoRoute, ...chatsListadoPopupRoute];

@NgModule({
  imports: [NewoApp3SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ChatsListadoComponent,
    ChatsListadoDetailComponent,
    ChatsListadoUpdateComponent,
    ChatsListadoDeleteDialogComponent,
    ChatsListadoDeletePopupComponent
  ],
  entryComponents: [
    ChatsListadoComponent,
    ChatsListadoUpdateComponent,
    ChatsListadoDeleteDialogComponent,
    ChatsListadoDeletePopupComponent
  ],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp3ChatsListadoModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}