import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Table } from 'primeng/primeng';
import { Observable } from 'rxjs';
import { Responsavel } from '../model/Responsavel';
import { Page } from '../components/util/page';
import { RequestUtil } from '../components/util/request-util';

@Injectable()
export class ResponsavelService {
    
    constructor(
        private httpClient: HttpClient
    ) { }

    listar(query: any, datatable: Table): Observable<Page<Responsavel>> {
        const options = { params: RequestUtil.getRequestParamsTable(datatable)};
        return this.httpClient.post<Page<Responsavel>>('http://localhost:4200/api/responsaveis/pesquisar', query, options);
    } 
}