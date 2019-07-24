import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NewoApp3SharedModule } from 'app/shared';
import {
  ChatGrupoComponent,
  ChatGrupoDetailComponent,
  ChatGrupoUpdateComponent,
  ChatGrupoDeletePopupComponent,
  ChatGrupoDeleteDialogComponent,
  chatGrupoRoute,
  chatGrupoPopupRoute
} from './';

const ENTITY_STATES = [...chatGrupoRoute, ...chatGrupoPopupRoute];

@NgModule({
  imports: [NewoApp3SharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ChatGrupoComponent,
    ChatGrupoDetailComponent,
    ChatGrupoUpdateComponent,
    ChatGrupoDeleteDialogComponent,
    ChatGrupoDeletePopupComponent
  ],
  entryComponents: [ChatGrupoComponent, ChatGrupoUpdateComponent, ChatGrupoDeleteDialogComponent, ChatGrupoDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp3ChatGrupoModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
