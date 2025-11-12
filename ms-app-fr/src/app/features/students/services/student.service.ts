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
    return this.httpClient
      .get<IStudentResponse>(`${this.BASE_URL}/students`)
      .pipe(
        map((res) => {
          if (res.data instanceof Array) {
            return res.data;
          }
          return [];
        })
      );
  }

  deleteStudent(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.BASE_URL}/students/${id}`);
  }

  addStudent(student: IStudent): Observable<IStudent> {
    return this.httpClient
      .post<IStudentResponse>(`${this.BASE_URL}/students`, student)

      .pipe(
        map((res) => {
          if (res.data instanceof Array) {
            return res.data[0];
          }
          return res.data;
        })
      );
  }
}
