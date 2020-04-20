package stryker4s.mutants.applymutants
import scala.meta.Term

object ActiveMutationContext {
  type ActiveMutationContext = Term.Name

  val envVar: ActiveMutationContext = Term.Name("env")
  val sysProps: ActiveMutationContext = Term.Name("props")
}
