package model

import org.scalatest.FunSuite

class AlterationTest extends FunSuite {
  val stunned = Alteration("Stn")
  test("The stunned alteration should inhibit all moves") {
    assert(
      stunned.inhibits(PhysicalAttack) &&
        stunned.inhibits(SpecialMove("SpecialMoveName", MoveType("Melee"), null, 0, 1)) &&
        stunned.inhibits(SpecialMove("SpecialMoveName", MoveType("Melee"), null, 0, 1)) &&
        stunned.inhibits(SpecialMove("SpecialMoveName", MoveType("Melee"), null, 0, 1)) &&
        stunned.inhibits(SpecialMove("SpecialMoveName", MoveType("Melee"), null, 0, 1)))
  }
  test("The stunned alteration should last 1 round") {
    assert(stunned.roundsDuration == 1)
  }
  test("The stunned alteration should not cause status variations") {
    assert(stunned.beginTurnStatusVariation.isEmpty)
  }

  val asleep = Alteration("Slp")
  test("The asleep alteration should inhibit all moves") {
    assert(
      asleep.inhibits(PhysicalAttack) &&
        asleep.inhibits(SpecialMove("SpecialMoveName", MoveType("Melee"), null, 0, 1)) &&
        asleep.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)) &&
        asleep.inhibits(SpecialMove("SpecialMoveName", MoveType("Ranged"), null, 0, 1)) &&
        asleep.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)))
  }
  test("The asleep alteration should last 3 rounds") {
    assert(asleep.roundsDuration == 3)
  }
  test("The asleep alteration should not cause status variations") {
    assert(asleep.beginTurnStatusVariation.isEmpty)
  }

  val poisoned = Alteration("Psn")
  test("The poisoned alteration should not inhibit any move") {
    assert(
      !poisoned.inhibits(PhysicalAttack) &&
        !poisoned.inhibits(SpecialMove("SpecialMoveName", MoveType("Melee"), null, 0, 1)) &&
        !poisoned.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)) &&
        !poisoned.inhibits(SpecialMove("SpecialMoveName", MoveType("Ranged"), null, 0, 1)) &&
        !poisoned.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)))
  }
  test("The poisoned alteration should last 3 rounds") {
    assert(poisoned.roundsDuration == 3)
  }
  test("The poisoned alteration should cause status variations") {
    assert(poisoned.beginTurnStatusVariation.isDefined)
  }

  val regeneration = Alteration("Reg")
  test("The regeneration alteration should not inhibit any move") {
    assert(
      !regeneration.inhibits(PhysicalAttack) &&
        !regeneration.inhibits(SpecialMove("SpecialMoveName", MoveType("Melee"), null, 0, 1)) &&
        !regeneration.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)) &&
        !regeneration.inhibits(SpecialMove("SpecialMoveName", MoveType("Ranged"), null, 0, 1)) &&
        !regeneration.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)))
  }
  test("The regeneration alteration should last 3 rounds") {
    assert(regeneration.roundsDuration == 2)
  }
  test("The regeneration alteration should cause status variations") {
    assert(regeneration.beginTurnStatusVariation.isDefined)
  }

  val berserk = Alteration("Brk")
  test("The berserk alteration should inhibit all moves except the physical attack") {
    assert(
      !berserk.inhibits(PhysicalAttack) &&
        berserk.inhibits(SpecialMove("SpecialMoveName", MoveType("Melee"), null, 0, 1)) &&
        berserk.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)) &&
        berserk.inhibits(SpecialMove("SpecialMoveName", MoveType("Ranged"), null, 0, 1)) &&
        berserk.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)))
  }
  test("The berserk alteration should last 3 rounds") {
    assert(berserk.roundsDuration == 3)
  }
  test("The berserk alteration should not cause status variations") {
    assert(berserk.beginTurnStatusVariation.isEmpty)
  }

  val silenced = Alteration("Sil")
  test("The silenced alteration should inhibit only spell moves") {
    assert(
      !silenced.inhibits(PhysicalAttack) &&
        !silenced.inhibits(SpecialMove("SpecialMoveName", MoveType("Melee"), null, 0, 1)) &&
        silenced.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)) &&
        !silenced.inhibits(SpecialMove("SpecialMoveName", MoveType("Ranged"), null, 0, 1)) &&
        silenced.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)))
  }
  test("The silenced alteration should last 2 rounds") {
    assert(silenced.roundsDuration == 2)
  }
  test("The silenced alteration should not cause status variations") {
    assert(silenced.beginTurnStatusVariation.isEmpty)
  }

  val frozen = Alteration("Frz")
  test("The frozen alteration should inhibit only melee moves") {
    assert(
      frozen.inhibits(PhysicalAttack) &&
        frozen.inhibits(SpecialMove("SpecialMoveName", MoveType("Melee"), null, 0, 1)) &&
        !frozen.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)) &&
        !frozen.inhibits(SpecialMove("SpecialMoveName", MoveType("Ranged"), null, 0, 1)) &&
        !frozen.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)))
  }
  test("The frozen alteration should last 2 rounds") {
    assert(frozen.roundsDuration == 2)
  }
  test("The frozen alteration should not cause status variations") {
    assert(frozen.beginTurnStatusVariation.isEmpty)
  }

  val blinded = Alteration("Bln")
  test("The blinded alteration should inhibit only ranged moves") {
    assert(
      !blinded.inhibits(PhysicalAttack) &&
        !blinded.inhibits(SpecialMove("SpecialMoveName", MoveType("Melee"), null, 0, 1)) &&
        !blinded.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)) &&
        blinded.inhibits(SpecialMove("SpecialMoveName", MoveType("Ranged"), null, 0, 1)) &&
        !blinded.inhibits(SpecialMove("SpecialMoveName", MoveType("Spell"), null, 0, 1)))
  }
  test("The blinded alteration should last 2 rounds") {
    assert(blinded.roundsDuration == 2)
  }
  test("The blinded alteration should not cause status variations") {
    assert(blinded.beginTurnStatusVariation.isEmpty)
  }
}
