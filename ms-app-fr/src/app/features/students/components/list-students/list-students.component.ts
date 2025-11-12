import { Component, inject, OnInit } from '@angular/core';
import { StudentService } from '../../services/student.service';
import { MatTable, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { IStudent } from '../../interfaces';

@Component({
  selector: 'app-list-students',
  imports: [MatTableModule, MatButtonModule, MatTable,MatIconModule],
  templateUrl: './list-students.component.html',
  styleUrl: './list-students.component.css',
})
export class ListStudentsComponent implements OnInit {
  private readonly studentService = inject(StudentService);

  students: IStudent[] = [];
  displayColumns: string[] = [
    'id',
    'studentCode',
    'firstName',
    'lastName',
    'dateOfBirth',
    'delete'
  ];

  ngOnInit(): void {
    this.studentService.getAllStudents().subscribe({
      next: (stdts) => {
        this.students = stdts;
      },
    });
  }

  addData() {}

  removeData() {}
}

const STUDENTS_RAW: IStudent[] = [
  {
    id: '1',
    studentCode: 'A10',
    firstName: 'Juan',
    lastName: 'Zapata',
    dateOfBirth: '2010-01-20',
  },
  {
    id: '1',
    studentCode: 'A10',
    firstName: 'Juan',
    lastName: 'Zapata',
    dateOfBirth: '2010-01-20',
  },
  {
    id: '1',
    studentCode: 'A10',
    firstName: 'Juan',
    lastName: 'Zapata',
    dateOfBirth: '2010-01-20',
  },
  {
    id: '1',
    studentCode: 'A10',
    firstName: 'Juan',
    lastName: 'Zapata',
    dateOfBirth: '2010-01-20',
  },
];
