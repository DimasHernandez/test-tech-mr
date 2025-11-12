import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

import { IStudent, IStudentResponse } from '../interfaces';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private readonly httpClient = inject(HttpClient);
  private readonly BASE_URL = environment.BASE_URL;

  getAllStudents(): Observable<IStudent[]> {
    return this.httpClient.get<IStudentResponse>(`${this.BASE_URL}/students`).pipe(
      map((res) => {
        if (res.data instanceof Array) {
          return res.data;
        }
        return [];
      })
    );
  }
}
