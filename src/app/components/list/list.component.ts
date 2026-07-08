import { ChangeDetectionStrategy, Component, OnInit, inject, signal } from '@angular/core';
import { RouterLink } from '@angular/router';

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
    this.people.set(this.personService.getAll());
  }

  delete(index: number): void {
    this.personService.remove(index);
    this.people.set(this.personService.getAll());
  }
}
