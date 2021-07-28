import { NgModule } from '@angular/core';
import { ResponsavelService } from '../services/responsavel.service';
import { TarefaService } from '../services/tarefa.service';
import { PRIMENG_IMPORTS } from './primeng-imports';

@NgModule({
    imports: [
        PRIMENG_IMPORTS
    ],
    providers: [ ResponsavelService, TarefaService ],
    exports: [
        PRIMENG_IMPORTS,
    ]
})
export class SharedModule { }
