import { ChangeDetectionStrategy, Component, OnInit, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { catchError, of } from 'rxjs';

import { Person } from '../../models/person';
import { PersonService } from '../../services/person.service';

@Component({
  selector: 'app-list',
  imports: [RouterLink],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ListComponent implements OnInit {
  private readonly personService = inject(PersonService);

  readonly people = signal<Person[]>([]);

  ngOnInit(): void {
    this.loadPeople();
  }

  delete(id?: number): void {
    if (id == null) {
      return;
    }
    this.personService.remove(id).subscribe(() => this.loadPeople());
  }

  private loadPeople(): void {
    this.personService
      .getAll()
      .pipe(catchError(() => of([])))
      .subscribe((people) => this.people.set(people));
  }
}
