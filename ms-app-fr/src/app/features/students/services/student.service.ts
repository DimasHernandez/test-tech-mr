import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { map, Observable, of } from 'rxjs';

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
          return this.getData(res);
        })
      );
  }

  findStudentById(id: string): Observable<IStudent> {
    return this.httpClient
      .get<IStudentResponse>(`${this.BASE_URL}/students/${id}`)
      .pipe(
        map((res) => {
          return this.getData(res);
        })
      )
  }

  updateStudent(id: string, student: IStudent): Observable<IStudent> {
    return this.httpClient
      .put<IStudentResponse>(`${this.BASE_URL}/students/${id}`, student)
      .pipe(
        map((res) => {
          return this.getData(res);
        })
      );
  }

  private getData(response: IStudentResponse): IStudent {
    if (response.data instanceof Array) {
      return response.data[0];
    }
    return response.data;
  }
}
