import { Component, inject, OnInit } from '@angular/core';
import { StudentService } from '../../services/student.service';
import { MatTable, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { IStudent } from '../../interfaces';
import { Router } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-list-students',
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatTable,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  templateUrl: './list-students.component.html',
  styleUrl: './list-students.component.css',
})
export class ListStudentsComponent implements OnInit {
  private readonly studentService = inject(StudentService);
  private readonly router = inject(Router);

  students: IStudent[] = [];
  displayColumns: string[] = [
    'id',
    'studentCode',
    'firstName',
    'lastName',
    'dateOfBirth',
    'delete',
  ];

  ngOnInit(): void {
    this.getStudents();
  }

  addStudent(): void {
    this.router.navigate(['/students/add']);
  }

  removeStudent(id: string) {
    this.studentService.deleteStudent(id).subscribe({
      next: () => {
        this.getStudents();
      },
    });
  }

  getStudents(): void {
    this.studentService.getAllStudents().subscribe({
      next: (stdts) => {
        this.students = stdts;
      },
    });
  }
}
