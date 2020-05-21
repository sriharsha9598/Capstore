import { TestBed } from '@angular/core/testing';

import { RaavanTeamService } from './raavan-team.service';

describe('RaavanTeamService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RaavanTeamService = TestBed.get(RaavanTeamService);
    expect(service).toBeTruthy();
  });
});
